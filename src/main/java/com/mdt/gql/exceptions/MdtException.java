package com.mdt.gql.exceptions;

import graphql.*;
import graphql.language.*;

import java.util.*;

public class MdtException extends RuntimeException implements GraphQLError {
    public MdtException(String msg) {
        super(msg);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }
}
