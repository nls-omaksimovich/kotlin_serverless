package com.example

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import com.google.gson.GsonBuilder

class Handler : RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private val mapper = GsonBuilder().setPrettyPrinting().create()
    override fun handleRequest(input: APIGatewayProxyRequestEvent, context: Context): APIGatewayProxyResponseEvent {
        val logger = context.logger

        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + mapper.toJson(System.getenv()))
        logger.log("CONTEXT: " + mapper.toJson(context))
        // process event
        logger.log("EVENT: " + mapper.toJson(input))

        return APIGatewayProxyResponseEvent()
            .withStatusCode(200)
            .withBody("Hello from Kotlin Lambda!")
    }
}
