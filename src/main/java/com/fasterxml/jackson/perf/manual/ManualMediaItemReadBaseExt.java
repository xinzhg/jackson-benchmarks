package com.fasterxml.jackson.perf.manual;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ManualMediaItemReadBaseExt extends ObjectReaderTestBase
{
	private final static MediaItem ITEM = MediaItems.stdMediaItem();

	private final ObjectMapper _mapper;

	public ManualMediaItemReadBaseExt(ObjectMapper m) {
		_mapper = m;
	}
	
	@Override
    protected int targetSizeMegs() { return 15; }
    
    protected final void test() throws Exception
    {
        doTestGenerateJsonString(_mapper, ITEM);
    }
}
