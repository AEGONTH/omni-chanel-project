package com.adms.web.bean.omni.travel;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/refreshDTCustomerTravel")
public class OmniTravelSocketChannel {

	@OnMessage(encoders = { JSONEncoder.class })
	public String onMessage(String data) {
		return data;
	}

}
