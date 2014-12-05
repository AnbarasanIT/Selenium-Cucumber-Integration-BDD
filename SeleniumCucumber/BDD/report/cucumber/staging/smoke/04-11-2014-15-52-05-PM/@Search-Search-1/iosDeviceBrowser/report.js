$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('search.feature');
formatter.feature({
  "id": "searching-the-product",
  "tags": [
    {
      "name": "@smoke",
      "line": 1
    },
    {
      "name": "@search",
      "line": 1
    }
  ],
  "description": "",
  "name": "Searching the product",
  "keyword": "Feature",
  "line": 3
});
formatter.scenario({
  "id": "searching-the-product;to-verify-whether-confirmation-page-is-displayed-when-user-creates-the-walgreens-account-with-loyalty-via-register-link-at-the-header",
  "tags": [
    {
      "name": "@Search-Search-1",
      "line": 5
    }
  ],
  "description": "",
  "name": "To verify whether confirmation page is displayed when user creates the walgreens account with loyalty via register link at the header",
  "keyword": "Scenario",
  "line": 6,
  "type": "scenario"
});
formatter.step({
  "name": "\"Search-Search-1\" Customer starts the scenario:",
  "keyword": "Given ",
  "line": 8,
  "rows": [
    {
      "cells": [
        "InputFileName",
        "SheetName",
        "RowId"
      ],
      "line": 9
    },
    {
      "cells": [
        "QC-Search",
        "Search",
        "Search-Search-1"
      ],
      "line": 10
    }
  ]
});
formatter.step({
  "name": "\"Search-Search-1\" Customer Search for a product using product ID:",
  "keyword": "Then ",
  "line": 13,
  "rows": [
    {
      "cells": [
        "InputFileName",
        "SheetName",
        "RowId"
      ],
      "line": 14
    },
    {
      "cells": [
        "CommonData",
        "search",
        "newproduct"
      ],
      "line": 15
    }
  ]
});
formatter.step({
  "name": "\"Search-Search-1\" Customer finished testing the scenario:",
  "keyword": "Then ",
  "line": 17,
  "rows": [
    {
      "cells": [
        "InputFileName",
        "SheetName",
        "RowId"
      ],
      "line": 18
    },
    {
      "cells": [
        "QC-Search",
        "Search",
        "Search-Search-1"
      ],
      "line": 19
    }
  ]
});
formatter.match({
  "arguments": [
    {
      "val": "Search-Search-1",
      "offset": 1
    }
  ],
  "location": "CommonSteps.Customer_starts_the_scenario(String,DataTable)"
});
formatter.result({
  "duration": 7513867000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Search-Search-1",
      "offset": 1
    }
  ],
  "location": "ProductIDSearchStep.Customer_Search_for_a_product_using_product_ID(String,DataTable)"
});
formatter.result({
  "duration": 3293379000,
  "status": "failed",
  "error_message": "org.junit.ComparisonFailure: expected:\u003c[1]\u003e but was:\u003c[0]\u003e\n\tat org.junit.Assert.assertEquals(Assert.java:115)\n\tat org.junit.Assert.assertEquals(Assert.java:144)\n\tat walgreens.ecom.batch.automation.library.common.CommonLibrary.ReportIt(CommonLibrary.java:766)\n\tat walgreens.ecom.batch.automation.stepdefinitions.marketing.vpdValidations.ProductIDSearchStep.Customer_Search_for_a_product_using_product_ID(ProductIDSearchStep.java:34)\n\tat ✽.Then \"Search-Search-1\" Customer Search for a product using product ID:(search.feature:13)\n"
});
formatter.match({
  "arguments": [
    {
      "val": "Search-Search-1",
      "offset": 1
    }
  ],
  "location": "CommonSteps.Customer_finished_testing_the_scenario(String,DataTable)"
});
formatter.result({
  "status": "skipped"
});
});