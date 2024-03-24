package com.autodata.api

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class HelloWorldDataFetcher {
  @DgsQuery fun helloWorld() = "Hello world!"
}
