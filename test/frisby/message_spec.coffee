frisby = require '../../tools/frisby/lib/frisby.js'

frisby.create('Get a simple message')
   .get('http://localhost:9000/message')
   .expectStatus(200)
   .expectHeaderContains('content-type', 'application/json')
   .expectJSON({
      message: "Hello Kitty"
   })
.toss()
