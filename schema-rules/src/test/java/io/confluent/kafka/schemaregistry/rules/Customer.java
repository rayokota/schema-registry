package io.confluent.kafka.schemaregistry.rules;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Customer extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7271457880488220349L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Customer\",\"namespace\":\"io.confluent.kafka.schemaregistry.rules\",\"fields\":[{\"name\":\"ssn\",\"type\":\"string\",\"confluent:tags\":[\"PII\"]},{\"name\":\"address\",\"type\":\"string\"},{\"name\":\"mail\",\"type\":\"string\"},{\"name\":\"user_id\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"int\"},{\"name\":\"IBAN\",\"type\":\"string\"},{\"name\":\"active\",\"type\":\"boolean\"},{\"name\":\"balance\",\"type\":\"float\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Customer> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Customer> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Customer> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Customer> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Customer> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Customer to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Customer from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Customer instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Customer fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence ssn;
  private java.lang.CharSequence address;
  private java.lang.CharSequence mail;
  private java.lang.CharSequence user_id;
  private int age;
  private java.lang.CharSequence IBAN;
  private boolean active;
  private float balance;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Customer() {}

  /**
   * All-args constructor.
   * @param ssn The new value for ssn
   * @param address The new value for address
   * @param mail The new value for mail
   * @param user_id The new value for user_id
   * @param age The new value for age
   * @param IBAN The new value for IBAN
   * @param active The new value for active
   * @param balance The new value for balance
   */
  public Customer(java.lang.CharSequence ssn, java.lang.CharSequence address, java.lang.CharSequence mail, java.lang.CharSequence user_id, java.lang.Integer age, java.lang.CharSequence IBAN, java.lang.Boolean active, java.lang.Float balance) {
    this.ssn = ssn;
    this.address = address;
    this.mail = mail;
    this.user_id = user_id;
    this.age = age;
    this.IBAN = IBAN;
    this.active = active;
    this.balance = balance;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
      case 0: return ssn;
      case 1: return address;
      case 2: return mail;
      case 3: return user_id;
      case 4: return age;
      case 5: return IBAN;
      case 6: return active;
      case 7: return balance;
      default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
      case 0: ssn = (java.lang.CharSequence)value$; break;
      case 1: address = (java.lang.CharSequence)value$; break;
      case 2: mail = (java.lang.CharSequence)value$; break;
      case 3: user_id = (java.lang.CharSequence)value$; break;
      case 4: age = (java.lang.Integer)value$; break;
      case 5: IBAN = (java.lang.CharSequence)value$; break;
      case 6: active = (java.lang.Boolean)value$; break;
      case 7: balance = (java.lang.Float)value$; break;
      default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'ssn' field.
   * @return The value of the 'ssn' field.
   */
  public java.lang.CharSequence getSsn() {
    return ssn;
  }


  /**
   * Sets the value of the 'ssn' field.
   * @param value the value to set.
   */
  public void setSsn(java.lang.CharSequence value) {
    this.ssn = value;
  }

  /**
   * Gets the value of the 'address' field.
   * @return The value of the 'address' field.
   */
  public java.lang.CharSequence getAddress() {
    return address;
  }


  /**
   * Sets the value of the 'address' field.
   * @param value the value to set.
   */
  public void setAddress(java.lang.CharSequence value) {
    this.address = value;
  }

  /**
   * Gets the value of the 'mail' field.
   * @return The value of the 'mail' field.
   */
  public java.lang.CharSequence getMail() {
    return mail;
  }


  /**
   * Sets the value of the 'mail' field.
   * @param value the value to set.
   */
  public void setMail(java.lang.CharSequence value) {
    this.mail = value;
  }

  /**
   * Gets the value of the 'user_id' field.
   * @return The value of the 'user_id' field.
   */
  public java.lang.CharSequence getUserId() {
    return user_id;
  }


  /**
   * Sets the value of the 'user_id' field.
   * @param value the value to set.
   */
  public void setUserId(java.lang.CharSequence value) {
    this.user_id = value;
  }

  /**
   * Gets the value of the 'age' field.
   * @return The value of the 'age' field.
   */
  public int getAge() {
    return age;
  }


  /**
   * Sets the value of the 'age' field.
   * @param value the value to set.
   */
  public void setAge(int value) {
    this.age = value;
  }

  /**
   * Gets the value of the 'IBAN' field.
   * @return The value of the 'IBAN' field.
   */
  public java.lang.CharSequence getIBAN() {
    return IBAN;
  }


  /**
   * Sets the value of the 'IBAN' field.
   * @param value the value to set.
   */
  public void setIBAN(java.lang.CharSequence value) {
    this.IBAN = value;
  }

  /**
   * Gets the value of the 'active' field.
   * @return The value of the 'active' field.
   */
  public boolean getActive() {
    return active;
  }


  /**
   * Sets the value of the 'active' field.
   * @param value the value to set.
   */
  public void setActive(boolean value) {
    this.active = value;
  }

  /**
   * Gets the value of the 'balance' field.
   * @return The value of the 'balance' field.
   */
  public float getBalance() {
    return balance;
  }


  /**
   * Sets the value of the 'balance' field.
   * @param value the value to set.
   */
  public void setBalance(float value) {
    this.balance = value;
  }

  /**
   * Creates a new Customer RecordBuilder.
   * @return A new Customer RecordBuilder
   */
  public static Customer.Builder newBuilder() {
    return new Customer.Builder();
  }

  /**
   * Creates a new Customer RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Customer RecordBuilder
   */
  public static Customer.Builder newBuilder(Customer.Builder other) {
    if (other == null) {
      return new Customer.Builder();
    } else {
      return new Customer.Builder(other);
    }
  }

  /**
   * Creates a new Customer RecordBuilder by copying an existing Customer instance.
   * @param other The existing instance to copy.
   * @return A new Customer RecordBuilder
   */
  public static Customer.Builder newBuilder(Customer other) {
    if (other == null) {
      return new Customer.Builder();
    } else {
      return new Customer.Builder(other);
    }
  }

  /**
   * RecordBuilder for Customer instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Customer>
      implements org.apache.avro.data.RecordBuilder<Customer> {

    private java.lang.CharSequence ssn;
    private java.lang.CharSequence address;
    private java.lang.CharSequence mail;
    private java.lang.CharSequence user_id;
    private int age;
    private java.lang.CharSequence IBAN;
    private boolean active;
    private float balance;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(Customer.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.ssn)) {
        this.ssn = data().deepCopy(fields()[0].schema(), other.ssn);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.address)) {
        this.address = data().deepCopy(fields()[1].schema(), other.address);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.mail)) {
        this.mail = data().deepCopy(fields()[2].schema(), other.mail);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.user_id)) {
        this.user_id = data().deepCopy(fields()[3].schema(), other.user_id);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.age)) {
        this.age = data().deepCopy(fields()[4].schema(), other.age);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.IBAN)) {
        this.IBAN = data().deepCopy(fields()[5].schema(), other.IBAN);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.active)) {
        this.active = data().deepCopy(fields()[6].schema(), other.active);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.balance)) {
        this.balance = data().deepCopy(fields()[7].schema(), other.balance);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing Customer instance
     * @param other The existing instance to copy.
     */
    private Builder(Customer other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.ssn)) {
        this.ssn = data().deepCopy(fields()[0].schema(), other.ssn);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.address)) {
        this.address = data().deepCopy(fields()[1].schema(), other.address);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.mail)) {
        this.mail = data().deepCopy(fields()[2].schema(), other.mail);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.user_id)) {
        this.user_id = data().deepCopy(fields()[3].schema(), other.user_id);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.age)) {
        this.age = data().deepCopy(fields()[4].schema(), other.age);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.IBAN)) {
        this.IBAN = data().deepCopy(fields()[5].schema(), other.IBAN);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.active)) {
        this.active = data().deepCopy(fields()[6].schema(), other.active);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.balance)) {
        this.balance = data().deepCopy(fields()[7].schema(), other.balance);
        fieldSetFlags()[7] = true;
      }
    }

    /**
     * Gets the value of the 'ssn' field.
     * @return The value.
     */
    public java.lang.CharSequence getSsn() {
      return ssn;
    }


    /**
     * Sets the value of the 'ssn' field.
     * @param value The value of 'ssn'.
     * @return This builder.
     */
    public Customer.Builder setSsn(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.ssn = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
     * Checks whether the 'ssn' field has been set.
     * @return True if the 'ssn' field has been set, false otherwise.
     */
    public boolean hasSsn() {
      return fieldSetFlags()[0];
    }


    /**
     * Clears the value of the 'ssn' field.
     * @return This builder.
     */
    public Customer.Builder clearSsn() {
      ssn = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
     * Gets the value of the 'address' field.
     * @return The value.
     */
    public java.lang.CharSequence getAddress() {
      return address;
    }


    /**
     * Sets the value of the 'address' field.
     * @param value The value of 'address'.
     * @return This builder.
     */
    public Customer.Builder setAddress(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.address = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
     * Checks whether the 'address' field has been set.
     * @return True if the 'address' field has been set, false otherwise.
     */
    public boolean hasAddress() {
      return fieldSetFlags()[1];
    }


    /**
     * Clears the value of the 'address' field.
     * @return This builder.
     */
    public Customer.Builder clearAddress() {
      address = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
     * Gets the value of the 'mail' field.
     * @return The value.
     */
    public java.lang.CharSequence getMail() {
      return mail;
    }


    /**
     * Sets the value of the 'mail' field.
     * @param value The value of 'mail'.
     * @return This builder.
     */
    public Customer.Builder setMail(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.mail = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
     * Checks whether the 'mail' field has been set.
     * @return True if the 'mail' field has been set, false otherwise.
     */
    public boolean hasMail() {
      return fieldSetFlags()[2];
    }


    /**
     * Clears the value of the 'mail' field.
     * @return This builder.
     */
    public Customer.Builder clearMail() {
      mail = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
     * Gets the value of the 'user_id' field.
     * @return The value.
     */
    public java.lang.CharSequence getUserId() {
      return user_id;
    }


    /**
     * Sets the value of the 'user_id' field.
     * @param value The value of 'user_id'.
     * @return This builder.
     */
    public Customer.Builder setUserId(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.user_id = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
     * Checks whether the 'user_id' field has been set.
     * @return True if the 'user_id' field has been set, false otherwise.
     */
    public boolean hasUserId() {
      return fieldSetFlags()[3];
    }


    /**
     * Clears the value of the 'user_id' field.
     * @return This builder.
     */
    public Customer.Builder clearUserId() {
      user_id = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
     * Gets the value of the 'age' field.
     * @return The value.
     */
    public int getAge() {
      return age;
    }


    /**
     * Sets the value of the 'age' field.
     * @param value The value of 'age'.
     * @return This builder.
     */
    public Customer.Builder setAge(int value) {
      validate(fields()[4], value);
      this.age = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
     * Checks whether the 'age' field has been set.
     * @return True if the 'age' field has been set, false otherwise.
     */
    public boolean hasAge() {
      return fieldSetFlags()[4];
    }


    /**
     * Clears the value of the 'age' field.
     * @return This builder.
     */
    public Customer.Builder clearAge() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
     * Gets the value of the 'IBAN' field.
     * @return The value.
     */
    public java.lang.CharSequence getIBAN() {
      return IBAN;
    }


    /**
     * Sets the value of the 'IBAN' field.
     * @param value The value of 'IBAN'.
     * @return This builder.
     */
    public Customer.Builder setIBAN(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.IBAN = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
     * Checks whether the 'IBAN' field has been set.
     * @return True if the 'IBAN' field has been set, false otherwise.
     */
    public boolean hasIBAN() {
      return fieldSetFlags()[5];
    }


    /**
     * Clears the value of the 'IBAN' field.
     * @return This builder.
     */
    public Customer.Builder clearIBAN() {
      IBAN = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
     * Gets the value of the 'active' field.
     * @return The value.
     */
    public boolean getActive() {
      return active;
    }


    /**
     * Sets the value of the 'active' field.
     * @param value The value of 'active'.
     * @return This builder.
     */
    public Customer.Builder setActive(boolean value) {
      validate(fields()[6], value);
      this.active = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
     * Checks whether the 'active' field has been set.
     * @return True if the 'active' field has been set, false otherwise.
     */
    public boolean hasActive() {
      return fieldSetFlags()[6];
    }


    /**
     * Clears the value of the 'active' field.
     * @return This builder.
     */
    public Customer.Builder clearActive() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
     * Gets the value of the 'balance' field.
     * @return The value.
     */
    public float getBalance() {
      return balance;
    }


    /**
     * Sets the value of the 'balance' field.
     * @param value The value of 'balance'.
     * @return This builder.
     */
    public Customer.Builder setBalance(float value) {
      validate(fields()[7], value);
      this.balance = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
     * Checks whether the 'balance' field has been set.
     * @return True if the 'balance' field has been set, false otherwise.
     */
    public boolean hasBalance() {
      return fieldSetFlags()[7];
    }


    /**
     * Clears the value of the 'balance' field.
     * @return This builder.
     */
    public Customer.Builder clearBalance() {
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Customer build() {
      try {
        Customer record = new Customer();
        record.ssn = fieldSetFlags()[0] ? this.ssn : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.address = fieldSetFlags()[1] ? this.address : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.mail = fieldSetFlags()[2] ? this.mail : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.user_id = fieldSetFlags()[3] ? this.user_id : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.age = fieldSetFlags()[4] ? this.age : (java.lang.Integer) defaultValue(fields()[4]);
        record.IBAN = fieldSetFlags()[5] ? this.IBAN : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.active = fieldSetFlags()[6] ? this.active : (java.lang.Boolean) defaultValue(fields()[6]);
        record.balance = fieldSetFlags()[7] ? this.balance : (java.lang.Float) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Customer>
      WRITER$ = (org.apache.avro.io.DatumWriter<Customer>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
      throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Customer>
      READER$ = (org.apache.avro.io.DatumReader<Customer>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
      throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
      throws java.io.IOException
  {
    out.writeString(this.ssn);

    out.writeString(this.address);

    out.writeString(this.mail);

    out.writeString(this.user_id);

    out.writeInt(this.age);

    out.writeString(this.IBAN);

    out.writeBoolean(this.active);

    out.writeFloat(this.balance);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
      throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.ssn = in.readString(this.ssn instanceof Utf8 ? (Utf8)this.ssn : null);

      this.address = in.readString(this.address instanceof Utf8 ? (Utf8)this.address : null);

      this.mail = in.readString(this.mail instanceof Utf8 ? (Utf8)this.mail : null);

      this.user_id = in.readString(this.user_id instanceof Utf8 ? (Utf8)this.user_id : null);

      this.age = in.readInt();

      this.IBAN = in.readString(this.IBAN instanceof Utf8 ? (Utf8)this.IBAN : null);

      this.active = in.readBoolean();

      this.balance = in.readFloat();

    } else {
      for (int i = 0; i < 8; i++) {
        switch (fieldOrder[i].pos()) {
          case 0:
            this.ssn = in.readString(this.ssn instanceof Utf8 ? (Utf8)this.ssn : null);
            break;

          case 1:
            this.address = in.readString(this.address instanceof Utf8 ? (Utf8)this.address : null);
            break;

          case 2:
            this.mail = in.readString(this.mail instanceof Utf8 ? (Utf8)this.mail : null);
            break;

          case 3:
            this.user_id = in.readString(this.user_id instanceof Utf8 ? (Utf8)this.user_id : null);
            break;

          case 4:
            this.age = in.readInt();
            break;

          case 5:
            this.IBAN = in.readString(this.IBAN instanceof Utf8 ? (Utf8)this.IBAN : null);
            break;

          case 6:
            this.active = in.readBoolean();
            break;

          case 7:
            this.balance = in.readFloat();
            break;

          default:
            throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}











