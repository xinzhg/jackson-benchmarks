package com.fasterxml.jackson.perf.manual;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

public class MediaItemWriteJson
    extends ObjectWriterTestBase<MediaItem, MediaItem>
{
    @Override
    protected int targetSizeMegs() { return 20; }

    public static void main(String[] args) throws Exception
    {
//    	final boolean USE_BYTES = true;
    	final boolean USE_AFTERBURNER = true;

    	if (args.length != 0) {
            System.err.println("Usage: java ...");
            System.exit(1);
        }
        String desc = "JSON";
        MediaItem input = MediaItems.stdMediaItem();
        ObjectMapper m = new ObjectMapper();
        if (USE_AFTERBURNER) {
        	m.registerModule(new AfterburnerModule());
        	desc += "+Afterburner";
        }
        new MediaItemWriteJson().test(m,
        		desc+"#1", input, MediaItem.class,
        		desc+"#2", input, MediaItem.class);
    }
}
