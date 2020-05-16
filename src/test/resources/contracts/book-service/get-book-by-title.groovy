import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return book by id"

    title = "Test Book"

    request {
        url "/api/v1/books?title=${title}"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body """
            {
                "id": "test-contract-id",
                "title": "${title}",
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
        """
    }
}