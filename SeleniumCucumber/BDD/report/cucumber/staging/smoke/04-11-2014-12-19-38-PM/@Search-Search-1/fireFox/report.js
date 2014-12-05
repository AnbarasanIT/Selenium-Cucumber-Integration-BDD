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
  "duration": 349578526000,
  "status": "failed",
  "error_message": "java.lang.OutOfMemoryError: Java heap space\n\tat java.awt.image.DataBufferInt.\u003cinit\u003e(DataBufferInt.java:41)\n\tat java.awt.image.Raster.createPackedRaster(Raster.java:455)\n\tat java.awt.image.DirectColorModel.createCompatibleWritableRaster(DirectColorModel.java:1015)\n\tat java.awt.image.BufferedImage.\u003cinit\u003e(BufferedImage.java:317)\n\tat walgreens.ecom.batch.automation.library.common.ReportingLibrary.takeScreenshot(ReportingLibrary.java:682)\n\tat walgreens.ecom.batch.automation.library.common.CommonLibrary.ReportIt(CommonLibrary.java:772)\n\tat walgreens.ecom.batch.automation.stepdefinitions.common.CommonSteps.Customer_starts_the_scenario(CommonSteps.java:207)\n\tat ✽.Given \"Search-Search-1\" Customer starts the scenario:(search.feature:8)\n"
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
  "status": "skipped"
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