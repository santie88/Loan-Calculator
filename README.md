# Loan-Calculator

* This application needs Java 8 to run
* Once compiled and run, it will expose the current endpoint:
    * URL: http://localhost:8080/generate-plan
    * Payload: 
    ```
    {
         "loanAmount": "5000",
         "nominalRate": "5.0",
         "duration": 24,
         "startDate": "2018-01-01T00:00:01Z"             
     }    
    ```
    * Response:
    ```
    [  
        { 
            "borrowerPaymentAmount": 219.36,  
            "date": "2018-01-01T00:00:01", 
            "initialOutstandingPrincipal": 5000.0, 
            "interest": 20.83, 
            "principal": 198.53, 
            "remainingOutstandingPrincipal": 4801.47 
        } 
        .... 
    ]
    ```
    
The Payload (Loan Amount, Nominal Rate, Duration and Start Date) will provide the input for the calculation of the detailed Repayment Plan.