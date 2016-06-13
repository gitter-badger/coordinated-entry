package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("pickListValue")
public class PickListValue extends ClientModel{


      private UUID pickListValueId;

      private String pickListValueCode;

      private String valueText;

      private UUID pickListGroupId;

      public PickListValue() {
  		super();
  	}


      public PickListValue(UUID pickListValueId) {
		super();
		this.pickListValueId = pickListValueId;
	}
	public UUID getPickListValueId(){
          return pickListValueId;
      }
      public void setPickListValueId(UUID pickListValueId){
          this.pickListValueId = pickListValueId;
      }
      public String getPickListValueCode(){
          return pickListValueCode;
      }
      public void setPickListValueCode(String pickListValueCode){
          this.pickListValueCode = pickListValueCode;
      }
      public String getValueText(){
          return valueText;
      }
      public void setValueText(String valueText){
          this.valueText = valueText;
      }
      public UUID getPickListGroupId(){
          return pickListGroupId;
      }
      public void setPickListGroupId(UUID pickListGroupId){
          this.pickListGroupId = pickListGroupId;
      }

 }
