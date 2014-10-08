package com.fasterxml.jackson.perf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

public abstract class ReadPerfBaseBasicJackson
	implements ReadPerfTestBasic
{
    protected final MinimalInputConverter MINIMAL_CONV;

    protected final ObjectReader MEDIA_ITEM_READER;

    protected ReadPerfBaseBasicJackson(MinimalInputConverter conv, ObjectMapper mapper) {
    	this(conv, mapper, null);
    }

    protected ReadPerfBaseBasicJackson(MinimalInputConverter conv, ObjectMapper mapper,
    		FormatSchema schema)
    {
    	MINIMAL_CONV = conv;
        ObjectReader r = mapper.reader(MediaItem.class);
        if (schema != null) {
        	r = r.with(schema);
        }
        MEDIA_ITEM_READER = r;
    }
    
    /*
    /**********************************************************************
    /* Typed reading tests
    /**********************************************************************
     */

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readPojoMediaItem(BlackHole bh) throws Exception {
        bh.consume(read(MINIMAL_CONV.bytesForMediaItem(), MEDIA_ITEM_READER));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    protected final Object read(byte[] input, ObjectReader reader) throws IOException {
        return reader.readValue(input);
    }
}
