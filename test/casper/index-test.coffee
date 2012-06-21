casper.test.comment 'Index page test suite'

casper.start 'http://localhost:9000/', () ->
  @test.assertTitle 'Welcome to Play 2.0'

casper.run () ->
  @test.done()
