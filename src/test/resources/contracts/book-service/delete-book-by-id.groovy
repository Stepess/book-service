import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should delete book by id"

    bookId = "test-contract-id"

    request {
        url "/api/v1/books/${bookId}"
        method DELETE()
    }

    response {
        status OK()
    }
}