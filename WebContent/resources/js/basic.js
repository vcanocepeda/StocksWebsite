

/**
 * Issues an HTTP get request in a new browser window. 
 * 
 * @param url  a given url.
 */
function openBrowserWindow(url) {
  window.open(url);
}

/**
 * This function is called to hide all the divs that are contained in a form.
 * 
 * @param divNumber 	number of divs (filters).
 */
function hideDivs(divNumber) {	  
	 var divs = $('#tabsContainer_paginatedListForm_filterPanel div');

	 var i=4;
	 if (divs[i].id == 'tabsContainer_paginatedListForm_filterByField') i = 7;
	 while (i<=divNumber-2)
	  {
		 $(divs[i]).hide();
		
		 if (divs[i].childNodes[1].childElementCount != 0) {		
			i = i+3;
		 };
		 i++; 
	  }
	 $(divs[divNumber-1]).hide();
}


/**
 * This function is called from filter panels when onchange events are trigered from the filter selector. Takes care of
 * showing the div corresponding to the selected filter option and hiding the others, besides clearing the hidden filter
 * options prior to form submission.
 * 
 * @param selectObject  a JQuery select object reference.
 */
function showSelectedFilterOption(selectObject) {
  var selectedValue = selectObject.selectedOption[0].label;
  var divs = $('#tabsContainer_paginatedListForm_filterPanel div');
  var divNumber = divs.length;
  
  if (selectedValue == selectObject.items[0].innerHTML) {
	  hideDivs(divNumber);
  } else {
  
	  var i=4;
	  var j=1;
	  if (divs[i].id == 'tabsContainer_paginatedListForm_filterByField') i = 7;
	  while (i<=divNumber-1)
	  {	  
		  if (selectedValue == selectObject.items[j].innerHTML) {
			  hideDivs(divNumber);
			  $(divs[i]).show();
			  if (divs[i].childNodes[1].childElementCount != 0)
			  {
				  $(divs[i+1]).show();
			  	  $(divs[i+2]).show();
			  	  $(divs[i+3]).show();
			  	  i = i+3;
			  }
			  $(divs[divNumber-1]).show();
			  break;
		  };
		  if (divs[i].childNodes[1].childElementCount != 0)
		  {
			  i = i+3;
		  };
		  j++;
		  i++;  
	  };
  };
}

/**
 * This function is called everytime we click the filter button to delete all the value previously set in other
 * filters.
 * 
 */

function deleteFilterValueFormOption() 
{	
	var idFilter = $('#tabsContainer_paginatedListForm_filterByField_input').val();
		
	//delete all previous values in all inputs
	var inputs = $('#tabsContainer_paginatedListForm_filterPanel input');	
	var i = 1;
	if (inputs[0].id == 'tabsContainer_paginatedListForm_favoritesOnlyField_input') i = 2;
	
	 while (i<=inputs.length -1) {
		if  (($('#'+idFilter)[0].children[1].id != inputs[i].id) &&
			(idFilter != inputs[i].parentElement.parentElement.id)) {			
			if ($(inputs[i]).attr('class') == 'ui-selectonemenu-label ui-inputfield ui-corner-all') {
				//$(inputs[i]).val('--');
				$(inputs[i].parentElement.children[0].children[0].options[0]).attr('selected', 'selected');
			} else {
				$(inputs[i]).val('');
			}
		}
		i++;
	 }
}

/**
 * This function is called everytime we update the form to draw the filter on screen, because everytime we make
 * an update the display of the fields sets to not visible.
 * 
 */

function keepFilterFormOption() 
{		 
	var idFilter = $('#tabsContainer_paginatedListForm_filterByField_input').val();
	if (idFilter != '') {
		$('#'+idFilter).show();
		$('#filterButton').show();
	}
}

/**
 * This function is designed to be called from the tabs of the application. Takes care of navigation between the different
 * tabs.
 *
 * TODO: send the user credentials in order to let server-side code to tell whether the request is authenticated 
 */
function handleOnTabShow() {
		//var activeIndex = tabsContainer.getActiveIndex();
		var hiddenBox = $( "#tabsContainer" );
	    var activeIndex = hiddenBox.getActiveIndex();
		var map = new Object();
		map[0] = 'index.xhtml';
		map[1] = '/Stocks/listStock.xhtml';
		map[2] = '/Markets/listMarket.xhtml';
		map[3] = 'userList.html?clearSession=true';
		//window.location.href = window.location.protocol + "//" + window.location.host + "/" + "StocksWebsite/" + map[activeIndex];
		window.location.href = "http://localhost:8080/StocksWebsite/Stocks/listStock.xhtml";
		//window.location = map[activeIndex];
 }

/**
 * This function performs redirection to the specified locationHref.
 *  
 * @param locationHref  a new location href.
 */
function changeDocumentLocationHref(locationHref) {
  document.location.href = locationHref; 
}

/**
 * This function recovers parameters when get request is submitted.
 *  
 * @param   name   name of the parameter we want to know
 * @returns value of the parameter we have passed.
 */
function gup( name )
{
	var regexS = "[\?&]"+name+"=([^&#]*)";
	var regex = new RegExp( regexS );
	var tmpURL = window.location.href;
	var results = regex.exec( tmpURL );
	if( results == null )
		return ;
	else
		return results[1];
}