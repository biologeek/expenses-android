package io.biologeek.expenses.api;

import java.util.List;

import io.biologeek.expenses.api.beans.Operation;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by xavier on 07/06/18.
 */

public interface OperationAPI {

    String apiSubPath = "operation";

    @GET(apiSubPath+"/account/{account}/operations/page/{pageNumber}")
    List<Operation> getPaginatedOperationList( @Path("account") Integer account,
                                               @Path("pageNumber") Integer pageNumber,
                                               @Query("limit") Integer limit,
                                               @Query("orderBy") String orderBy,
                                               @Query("reverse") boolean reverse);
}
