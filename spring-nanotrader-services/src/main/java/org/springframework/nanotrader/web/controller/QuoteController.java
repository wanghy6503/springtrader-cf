/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.nanotrader.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.nanotrader.data.domain.Quote;
import org.springframework.nanotrader.data.service.QuoteService;
import org.springframework.nanotrader.service.domain.CollectionResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Provides JSON based REST api to Quote repository
 * 
 * @author Brian Dussault
 * @author Kashyap Parikh
 */
@Controller
public class QuoteController  {

	@Autowired
	@Qualifier( "rtQuoteService")
	private QuoteService quoteService;

	@RequestMapping(value = "/quote/{symbol}", method = RequestMethod.GET)
	public ResponseEntity<Quote> findQuote(
			@PathVariable("symbol") final String symbol) {
		Quote responseQuote = quoteService.findBySymbol(symbol);

		if(responseQuote == null) {
			return new ResponseEntity<Quote>(BaseController.getNoCacheHeaders(),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Quote>(responseQuote, BaseController.getNoCacheHeaders(),
				HttpStatus.OK);

	}

	@RequestMapping(value = "/quotes", method = RequestMethod.GET)
	@ResponseBody
	public CollectionResult findQuotes() {
		CollectionResult cr = new CollectionResult();
		cr.setResults(quoteService.findAllQuotes());
		return cr;
	}

	@RequestMapping(value = "/quote", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public void post() {
	}

	@RequestMapping(value = "/quote/{symbol}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public void put() {

	}

	@RequestMapping(value = "/quote/{symbol}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public void delete() {

	}

}
