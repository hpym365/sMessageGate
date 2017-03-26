package com.senyint.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jl7.hl7.HL7Field;
import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;
import org.jl7.hl7.HL7Segment;

// 看看hl7v2
public class Hl7Utils {

	public Map<String, String> converHl7toMap(String msg) {
		HL7Message hl7msg = HL7Parser.parseMessage(msg, true);

		Map<String, String> map = new HashMap<String, String>();
		List<HL7Segment> segments = hl7msg.getSegments();

		for (int i = 0; i < segments.size(); i++) {
			HL7Segment hl7Segment = segments.get(i);
			ArrayList<HL7Field> fields = hl7Segment.getFields();
			for (int j = 0; j < fields.size(); j++) {
				map.put(hl7Segment.getSegmentType() + i + j, fields.get(j).getValue());
				// System.out.println(fields);
			}
		}

		return map;
	}
}
