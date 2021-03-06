package us.ihmc.idl.generated.test;

import us.ihmc.communication.packets.Packet;
import us.ihmc.euclid.interfaces.Settable;
import us.ihmc.euclid.interfaces.EpsilonComparable;
import java.util.function.Supplier;
import us.ihmc.pubsub.TopicDataType;

public class FooEnumType extends Packet<FooEnumType> implements Settable<FooEnumType>, EpsilonComparable<FooEnumType>
{
   public java.lang.StringBuilder name_;
   public us.ihmc.idl.IDLSequence.StringBuilderHolder  enumValues_;

   public FooEnumType()
   {
      name_ = new java.lang.StringBuilder(255);
      enumValues_ = new us.ihmc.idl.IDLSequence.StringBuilderHolder (255, "type_d");
   }

   public FooEnumType(FooEnumType other)
   {
      this();
      set(other);
   }

   public void set(FooEnumType other)
   {
      name_.setLength(0);
      name_.append(other.name_);

      enumValues_.set(other.enumValues_);
   }

   public void setName(java.lang.String name)
   {
      name_.setLength(0);
      name_.append(name);
   }

   public java.lang.String getNameAsString()
   {
      return getName().toString();
   }
   public java.lang.StringBuilder getName()
   {
      return name_;
   }


   public us.ihmc.idl.IDLSequence.StringBuilderHolder  getEnumValues()
   {
      return enumValues_;
   }


   public static Supplier<FooEnumTypePubSubType> getPubSubType()
   {
      return FooEnumTypePubSubType::new;
   }

   @Override
   public Supplier<TopicDataType> getPubSubTypePacket()
   {
      return FooEnumTypePubSubType::new;
   }

   @Override
   public boolean epsilonEquals(FooEnumType other, double epsilon)
   {
      if(other == null) return false;
      if(other == this) return true;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsStringBuilder(this.name_, other.name_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsStringBuilderSequence(this.enumValues_, other.enumValues_, epsilon)) return false;


      return true;
   }

   @Override
   public boolean equals(Object other)
   {
      if(other == null) return false;
      if(other == this) return true;
      if(!(other instanceof FooEnumType)) return false;

      FooEnumType otherMyClass = (FooEnumType) other;

      if (!us.ihmc.idl.IDLTools.equals(this.name_, otherMyClass.name_)) return false;

      if (!this.enumValues_.equals(otherMyClass.enumValues_)) return false;

      return true;
   }

   @Override
   public java.lang.String toString()
   {
      StringBuilder builder = new StringBuilder();

      builder.append("FooEnumType {");
      builder.append("name=");
      builder.append(this.name_);      builder.append(", ");
      builder.append("enumValues=");
      builder.append(this.enumValues_);
      builder.append("}");
      return builder.toString();
   }
}
