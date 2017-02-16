package us.ihmc.idl.generated.Chat;

import java.io.IOException;

import us.ihmc.idl.CDR;
import us.ihmc.pubsub.TopicDataType;
import us.ihmc.pubsub.common.SerializedPayload;

public class ChatMessagePubSubType implements TopicDataType<ChatMessage>
{
	public static final String name = "Chat::ChatMessage";
	private final CDR serializeCDR = new CDR();
	private final CDR deserializeCDR = new CDR();
	
	
	
    public ChatMessagePubSubType()
    {
        
    }
    
       @Override
   public void serialize(ChatMessage data, SerializedPayload serializedPayload) throws IOException
   {
      serializeCDR.serialize(serializedPayload);
      data.serialize(serializeCDR);
      serializeCDR.finishSerialize();
   }

   @Override
   public void deserialize(SerializedPayload serializedPayload, ChatMessage data) throws IOException
   {
      deserializeCDR.deserialize(serializedPayload);
      data.deserialize(deserializeCDR);
      deserializeCDR.finishDeserialize();
   }

   @Override
   public int getTypeSize()
   {
      return ChatMessage.getMaxCdrSerializedSize();
   }

   @Override
   public String getName()
   {
      return name;
   }

   @Override
   public ChatMessage createData()
   {
      return new ChatMessage();
   }
}