package com.yahoo.ycsb.db.angra;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
*Class with JSON methods to convert docs.
*/

public class JsonUtil {

  private static final Logger LOGGER = Logger.getLogger(Driver.class.getName());

  /**
  * Encode in JSON a given string in a named field.
  *
  * @param s is the string to be encoded.
  * @param field is the field name to be encoded.
  *
  */
  public String encode(String s, String field){
    JSONObject obj = new JSONObject();
    obj.put(field, s);
    StringWriter out = new StringWriter();
    try{
      obj.writeJSONString(out);
    } catch (Exception e){
      LOGGER.log(Level.SEVERE,
          "JSON encode: Could not encode JSON, due the following exception: ", e);
    }
    return out.toString();
  }

  /**
  * Decode in JSON value in a given field.
  *
  * @param j JSON encoded string.
  * @param field is the field name to get value.
  *
  */
  public String decode(String j, String field){
    JSONParser parser = new JSONParser();
    String toReturn = "";
    try{
      JSONArray array = (JSONArray)parser.parse(j);
      JSONObject obj = (JSONObject)array.get(0);
      toReturn = obj.get(field).toString();
    } catch (Exception e){
      LOGGER.log(Level.SEVERE,
          "JSON decode: Could not decode JSON, due the following exception: ", e);
    }

    return toReturn;
  }
}
