package us.ihmc.idl;

import java.nio.ByteBuffer;

import org.apache.commons.lang3.NotImplementedException;

import us.ihmc.rtps.common.SerializedPayload;

public class CDR
{
   private ByteBuffer buf;
   
   public static int alignment(int current_alignment, int dataSize)
   {
      return (dataSize - (current_alignment % dataSize)) & (dataSize-1);
   }
   
   
   public void serialize(SerializedPayload payload)
   {
      buf = payload.getData();
      buf.putShort(payload.getEncapsulation());
   }
   
   public void deserialize(SerializedPayload payload)
   {
      buf = payload.getData();
   }

   
   /**
    * Signed short
    */
   public short read_type_1()
   {
      align(2);
      return buf.getShort();
   }
   
   public void write_type_1(short val)
   {
      align(2);
      buf.putShort(val);
   }
   
   /**
    * Signed int
    */
   public int read_type_2()
   {
      align(4);
      return buf.getInt();
   }
   
   public void write_type_2(int val)
   {
      align(4);
      buf.putInt(val);
   }
   
   /**
    * Unsigned short
    */
   public int read_type_3()
   {
      return read_type_1() & 0xFFFF;
   }
   
   public void write_type_3(int val)
   {
      write_type_1((short) val);
   }
   
   /**
    * Unsigned int
    */
   public long read_type_4()
   {
      return read_type_2() & 0xFFFFFFFF;
   }
   
   public void write_type_4(long val)
   {
      write_type_2((int) val);
   }
   
   /**
    * Float
    */
   public float read_type_5() 
   {
      align(4);
      return buf.getFloat();
   }
   
   public void write_type_5(float val)
   {
      align(4);
      buf.putFloat(val);
   }
   
   /**
    * Double
    */
   public double read_type_6()
   {
      align(8);
      return buf.getDouble();
   }
   
   public void write_type_6(double val)
   {
      align(8);
      buf.putDouble(val);
   }
   
   /**
    * Boolean
    */
   public boolean read_type_7()
   {
      return read_type_9() != (byte)0;
   }
   
   public void write_type_7(boolean val)
   {
      write_type_9(val?(byte)1:(byte)0);
   }
   
   /**
    * Char
    */
   public char read_type_8()
   {
      return (char)(buf.get() & 0xFF);
   }
   
   public void write_type_8(char val)
   {
      buf.put((byte)val);
   }
   
   /**
    * Octet
    */
   public byte read_type_9()
   {
      return buf.get();
   }
   
   public void write_type_9(byte val)
   {
      buf.put(val);
   }
   
   /**
    * Struct
    */
   public void read_type_a(IDLStruct type)
   {
      type.deserialize(this);
   }
   
   public void write_type_a(IDLStruct type)
   {
      type.serialize(this);
   }
   
   /**
    * Union
    */
   public Object read_type_b()
   {
      throw new NotImplementedException("Union types are not implemented yet");
   }
   
   public void write_type_b(Object object)
   {
      throw new NotImplementedException("Union types are not implemented yet");
   }
   
   /**
    * Enum
    */
   
   public int read_type_c()
   {
      return read_type_2();
   }
   
   public void write_type_c(int val)
   {
      write_type_2(val);
   }
   
   /**
    * String
    */
   public void read_type_d(StringBuilder res)
   {
      int length = read_type_2();
      res.setLength(length);
      for(int i = 0; i < length; i++)
      {
         res.setCharAt(i, (char)buf.get());
      }
   }
   
   public void write_type_d(StringBuilder str)
   {
      write_type_2(str.length());
      for(int i = 0; i < str.length(); i++)
      {
         buf.put((byte)str.charAt(i));
      }
   }
   
   /**
    * Sequence
    */
   public void read_type_e(IDLSequence seq)
   {
      int length = read_type_2();
      seq.resetQuick();
      for(int i = 0; i < length; i++)
      {
         seq.readElement(i, this);
      }
   }
   
   public void write_type_e(IDLSequence seq)
   {
      int length = seq.size();
      write_type_2(length);
      for(int i = 0; i < length; i++)
      {
         seq.writeElement(i, this);
      }
   }
   
   /** 
    * Array
    */
   public void read_type_f()
   {
      
   }
   
   
   /**
    * Signed long, 64 bit
    */
   public long read_type_11()
   {
      align(8);
      return buf.getLong();
   }
   
   public void write_type_11(long val)
   {
      align(8);
      buf.putLong(val);
   }
   
   /**
    * Unsigned long, 64bit
    */
   public long read_type_12()
   {
      return read_type_11();
   }
   
   public void write_type_12(long val)
   {
      write_type_11(val);
   }
   
   /**
    * Long doubles (16 bytes, unsupported)
    */
   public double read_type_13()
   {
      throw new NotImplementedException("Java does not support 16 byte Double values");
   }
   
   public void write_type_13(double val)
   {
      throw new NotImplementedException("Java does not support 16 byte Double values");
   }
   
   /**
    * Wide char (16 bits)
    */
   public char read_type_14()
   {
      align(2);
      return buf.getChar();
   }
   
   public void write_type_14(char val)
   {
      align(2);
      buf.putChar(val);
   }

   /**
    * Wide string
    */
   public void read_type_15(StringBuilder res)
   {
      int length = read_type_2();
      res.setLength(length);
      for(int i = 0; i < length; i++)
      {
         res.setCharAt(i, buf.getChar());
      }
   }
   
   public void write_type_15(StringBuilder str)
   {
      write_type_2(str.length());
      for(int i = 0; i < str.length(); i++)
      {
         buf.putChar(str.charAt(i));
      }
   }   

   
   
   public int align(int byteBoundary) {
      int position = buf.position();
      int adv = (position % byteBoundary);

      if (adv != 0) {
         buf.position(position + (byteBoundary - adv));
      }
      
      return adv;
  }
   
   
}