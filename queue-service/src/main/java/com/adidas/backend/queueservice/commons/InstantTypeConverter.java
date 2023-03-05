package com.adidas.backend.queueservice.commons;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.Instant;

public class InstantTypeConverter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {

  /**
   * The custom serializer for Instant
   * @param src Instant
   * @param srcType Type
   * @param context JsonSerializationContext
   * @return JsonElement
   */
  @Override
  public JsonElement serialize(Instant src, Type srcType, JsonSerializationContext context) {
    return new JsonPrimitive(src.toEpochMilli());
  }

  /**
   * The custom deserializer for Instant
   * @param json JsonElement
   * @param type Type
   * @param context JsonDeserializationContext
   * @return Instant
   * @throws JsonParseException JsonParseException
   */
  @Override
  public Instant deserialize(JsonElement json, Type type, JsonDeserializationContext context)
      throws JsonParseException {
    return Instant.ofEpochMilli(json.getAsLong());
  }

}
