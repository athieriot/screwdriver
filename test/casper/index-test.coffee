casper.test.comment 'Index page test suite'

casper.start 'http://localhost:9000/', () ->
  @test.assertTitle 'Welcome to Screwdriver'

casper.run () ->
  @test.done()
