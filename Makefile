all: unit integ

unit:
	play test

func:
	./node_modules/.bin/jasmine-node --coffee --verbose ./test/frisby

integ:
	./tools/casperjs/bin/casperjs test ./test/casper/

debug:
	play debug

start:
	nohup play start

stop:
	play stop

run:
	play run

deploy:
	git push heroku master

.PHONY: test
