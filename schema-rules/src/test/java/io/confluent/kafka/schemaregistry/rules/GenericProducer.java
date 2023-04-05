package io.confluent.kafka.schemaregistry.rules;

import io.confluent.kafka.serializers.WrapperKeySerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

    import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericProducer {

  private static final Logger logger = LoggerFactory.getLogger(GenericProducer.class);
  private static Properties props;
  private String topic;

  GenericProducer(String propertiesFile) {
    try {
      props = ClientsUtils.loadConfig(propertiesFile);
      props.put("key.serializer", WrapperKeySerializer.class.getName());
      props.put("wrapped.key.serializer", StringSerializer.class.getName());
      props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
      props.put("auto.register.schemas", "false");
      props.put("use.latest.version", "true");
      props.put("latest.compatibility.strict", "false");

      // CEL rules executor
      props.put("rule.executors", "checkLen");
      //props.put("rule.executors", "checkLen,changeUserId");
      props.put("rule.executors.checkLen.class", "io.confluent.kafka.schemaregistry.rules.cel.CelExecutor");
      //props.put("rule.executors.changeUserId.class", "io.confluent.kafka.schemaregistry.rules.cel.CelFieldExecutor");

      // DLQ settings
      props.put("rule.actions", "action1"); // this name is arbitrary
      props.put("rule.actions.action1.class","io.confluent.kafka.schemaregistry.rules.DlqAction");
      props.put("rule.actions.action1.param.topic", "DLQ");
      props.put("rule.actions.action1.param.bootstrap.servers", "localhost:9092");
      //props.put("rule.actions.action1.param.key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
      //props.put("rule.actions.action1.param.value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
      //props.put("rule.acitons.action1.param.max.in.flight.requests.per.connection", "1");

      topic = "customer"; // props.getProperty("topic");
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("Error in constructor: " + e.getMessage());
    }
  }

  public void runProducer() {

    // Set the key and value
    String key = "test";
    Customer value = new Customer();
    value.setSsn("123456789"); // rule: size = 9 digits
    value.setAddress("#10 abc, CA 94402"); // rule: matches regex
    value.setMail("david@confluent.io"); // rule: contains @
    value.setUserId("uid_23434"); // rule: starts with uid_
    value.setAge(45); // rule: > 18
    value.setIBAN("GB33BUKB20201555555555"); // rule: matches regex
    //value.setActive(true); // rule: is true
    value.setActive(false); // rule: is true
    value.setBalance(new Float(10.0).floatValue()); // rule: >= 0.0

        /*
        {"name":"ssn","type":"string","confluent:tags":["PII"]},
        {"name":"address","type":"string"},
        {"name":"mail","type":"string"},
        {"name":"user_id","type":"string"},
        {"name":"age","type":"int"},
        {"name":"IBAN","type":"long"},
        {"name":"active","type":"boolean"},
        {"name":"balance","type":"float"}
         */

    System.out.println("debug 1");
    // Create a producer
    Producer producer = null;
    try {
    producer = new KafkaProducer<>(props);
    System.out.println("debug 1.5");

    // Create a record
    ProducerRecord<String, Object> record = new ProducerRecord<>(topic, key, value);
    System.out.println("debug 1.6");

    System.out.println("debug 2");


    System.out.println("debug 3");

      // Produce the record
      RecordMetadata metadata = (RecordMetadata) producer.send(record).get();
      System.out.println("debug 4");

      System.out.printf("Record sent with key %s and value %s to partition %d with offset %d%n",
          key, value, metadata.partition(), metadata.offset());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // Close the producer
      if (producer != null) producer.close();
    }
  }

  public static void main(final String[] args) throws Exception {
    int numArgs = args.length;
    //if (numArgs < 2) {
    //  logger.error("Required arguments: properties-file value");
    //  System.exit(1);
    //} else {
      try {
        GenericProducer producer = new GenericProducer("/Users/ryokota/notes/cp.properties");
        producer.runProducer();
      } catch (Exception e) {
        logger.error("Error in GenericProducer.main method: " + e.getMessage());
      }
      Thread.sleep(5000);
    //}
  }
}

