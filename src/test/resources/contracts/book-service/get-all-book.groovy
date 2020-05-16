import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return book all book in pageable format"

    bookId = "test-contract-id"

    request {
        url "/api/v1/books"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body """
              {
                "content": [
                  {
                    "id": "${bookId}",
                    "title": "Test Book",
                    "description": "Awesome book about testing",
                    "price": 42.13,
                    "quantity": 11,
                    "authors": [
                      {
                        "firstName": "Stepan",
                        "lastName": "Yershov"
                      }
                    ],
                    "genres": [
                      "kek",
                      "lol"
                    ],
                    "tags": [
                      "bestseller"
                    ]
                  }
                ],
                "pageable": {
                  "sort": {
                    "sorted": true,
                    "unsorted": false,
                    "empty": false
                  },
                  "pageNumber": 0,
                  "pageSize": 20,
                  "offset": 0,
                  "paged": true,
                  "unpaged": false
                },
                "totalPages": 1,
                "totalElements": 1,
                "last": true,
                "first": true,
                "numberOfElements": 1,
                "sort": {
                  "sorted": true,
                  "unsorted": false,
                  "empty": false
                },
                "size": 20,
                "number": 0,
                "empty": false
              }
        """
    }
}