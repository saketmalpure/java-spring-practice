package com.sample.lambdaspring;

import com.amazonaws.services.lambda.runtime.*;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class LambdaHandler extends AbstractHandler<AppConfig> implements RequestHandler<SNSEvent, Object> {

    private final Logger LOGGER = LoggerFactory.getLogger(LambdaHandler.class);

    private ApplicationContext applicationContext;

    @Override
    public Object handleRequest(SNSEvent request, Context context) {

        SNSEvent.SNS sns = request.getRecords().get(0).getSNS();
        String message = sns.getMessage();
        try{
            applicationContext = getApplicationContext();
            ProxyService service = applicationContext.getBean(ProxyService.class);
            service.helloWorld(message);
        } catch(Exception e){
            return new RuntimeException(e);
        }

        return "Success";
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        LambdaHandler lambdaHandler = new LambdaHandler();
        SNSEvent snsEvent = new SNSEvent();
        SNSEvent.SNSRecord snsRecord = new SNSEvent.SNSRecord();

        SNSEvent.SNS sns = new SNSEvent.SNS();
        sns.setMessage("Hello World");
        snsRecord.setSns(sns);

        List<SNSEvent.SNSRecord> snsRecordList = new ArrayList<SNSEvent.SNSRecord>();
        snsRecordList.add(snsRecord);
        snsEvent.setRecords(snsRecordList);

        lambdaHandler.handleRequest(snsEvent, new Context() {
            @Override
            public String getAwsRequestId() {
                return null;
            }

            @Override
            public String getLogGroupName() {
                return null;
            }

            @Override
            public String getLogStreamName() {
                return null;
            }

            @Override
            public String getFunctionName() {
                return null;
            }

            @Override
            public String getFunctionVersion() {
                return null;
            }

            @Override
            public String getInvokedFunctionArn() {
                return null;
            }

            @Override
            public CognitoIdentity getIdentity() {
                return null;
            }

            @Override
            public ClientContext getClientContext() {
                return null;
            }

            @Override
            public int getRemainingTimeInMillis() {
                return 0;
            }

            @Override
            public int getMemoryLimitInMB() {
                return 0;
            }

            @Override
            public LambdaLogger getLogger() {
                return new LambdaLogger() {
                    @Override
                    public void log(String string) {

                    }
                };
            }
        });
    }
}
