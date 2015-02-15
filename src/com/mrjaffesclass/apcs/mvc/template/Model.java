package com.mrjaffesclass.apcs.mvc.template;

import com.mrjaffesclass.apcs.messenger.*;
import java.text.*;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageHandler {

  // Messaging system for the MVC
  private final Messenger mvcMessaging;
  // Model's data variables
  private double Age2;
  private double Age;
  private double minAge;
  private double maxAge;
  private int Progress;

  /**
   * Model constructor: Create the data representation of the program
   * @param messages Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public Model(Messenger messages) {
    mvcMessaging = messages;
  }
  
  /**
   * Initialize the model here and subscribe to any required messages
   */
  public void init() {
    mvcMessaging.subscribe("view:computeButton", this);
    mvcMessaging.subscribe("view:computeButton1", this);
  }
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    NumberFormat fmt = NumberFormat.getNumberInstance();
    fmt.setMaximumFractionDigits(0);
    fmt.setMinimumFractionDigits(0);
      if (messagePayload != null) {
      System.out.println("MSG: received by model: "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("MSG: received by model: "+messageName+" | No data sent");
    }
    MessagePayload payload = (MessagePayload)messagePayload;
      switch (messageName) {
          case "view:computeButton":                          
              Age = Double.parseDouble(payload.getAge());
              if (Age >= 15){
//                   Age = Integer.parseInt(payload.getAge());
                String x = fmt.format((Age/2)+7);  
                minAge = Math.abs(Double.parseDouble(x));
                String y = fmt.format((Age-7)*2);
                maxAge = Math.abs(Double.parseDouble(y));              
                mvcMessaging.notify("model:minAge", minAge, true);
                mvcMessaging.notify("model:maxAge", maxAge, true);
              }
              else {
                mvcMessaging.notify("model:error", 1 , true);
              }
              break;
           case "view:computeButton1":                 
              Age2 = Double.parseDouble(payload.getAge());
              Progress = (Age2 > minAge)?  (Age2 < maxAge)? 0:1:1;
              mvcMessaging.notify("model:Check",Progress, true);
              break;
      }
  }


}
