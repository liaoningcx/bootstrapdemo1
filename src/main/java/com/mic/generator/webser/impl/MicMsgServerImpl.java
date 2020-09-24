package com.mic.generator.webser.impl;

import com.mic.generator.webser.MicMsgServer;

import javax.jws.WebService;

/**
 * Created by caoxue on 2015/12/15.
 */
@WebService(endpointInterface = "com.mic.generator.webser.MicMsgServer")
public class MicMsgServerImpl implements MicMsgServer {
    @Override
    public String testWS(String msg) {
        String ss = "test Success dbcxu :";
        return ss+msg;
    }
}
