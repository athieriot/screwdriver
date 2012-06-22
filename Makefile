all: unit integ

unit:
	play test

func:
	./node_modules/.bin/jasmine-node --coffee --verbose ./test/frisby

integ:
	./tools/casperjs/bin/casperjs test ./test/casper/

debug:
	play debug

stage:
	play stage

start: stage
	nohup target/start &

stop:
	play stop

run:
	play run

clean:
	play clean

deploy:
	git push heroku master

.PHONY: test
