package com.mrjaffesclass.apcs.mvc.template;

/**
 * This is the message payload class.  Instantiate this class when sending
 * field / value message data for the up/down buttons
 * 
 * @author Roger Jaffe
 * @version 1.0
 */
public class MessagePayload {
  private final String Age;
  //private final int directionY;
  /**
   * Class constructor
   * @param _Age Direction (1 - 8)
   * @param _Age2 Direction (1 - 8)
   */
  public MessagePayload(String _Age) {
    Age = _Age;
  
  }
  /**
   * Getter method for Age
   * @return Age
   */
  public String getAge() {
    return Age;     
  }
}
