package com.fasterxml.jackson.perf.manual;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

public class MediaItemReadJsonExt extends ManualMediaItemReadBaseExt
{
	public MediaItemReadJsonExt(ObjectMapper m) {
		super(m);
	}

	public static void main(String[] args) throws Exception
    {
    	final boolean USE_BYTES = false;
    	final boolean USE_AFTERBURNER = true;

    	JsonFactory f = new JsonFactory();
        String desc = "JSON";
        ObjectMapper m = new ObjectMapper(f);
        if (USE_AFTERBURNER) {
        	m.registerModule(new AfterburnerModule());
        	desc += "+Afterburner";
        }
        if (USE_BYTES) {
        	desc += "(bytes)";
        } else {
        	desc += "(String)";
        }
        new MediaItemReadJsonExt(m).test();
    }
}
