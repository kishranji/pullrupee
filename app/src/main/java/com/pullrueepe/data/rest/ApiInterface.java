package com.pullrueepe.data.rest;


import com.pullrueepe.model.funds.CreateFund;
import com.pullrueepe.model.funds.CreateFundRequest;
import com.pullrueepe.model.funds.Funds;
import com.pullrueepe.model.funds.FundsRequest;
import com.pullrueepe.model.holdings.HoldingRequest;
import com.pullrueepe.model.holdings.HoldingResult;
import com.pullrueepe.model.login.LoginRequest;
import com.pullrueepe.model.login.LoginResponse;
import com.pullrueepe.model.monthlyTarget.CreateMonthlyRequest;
import com.pullrueepe.model.monthlyTarget.CreateMonthlyTarget;
import com.pullrueepe.model.monthlyTarget.GetAllMonthlyRequest;
import com.pullrueepe.model.monthlyTarget.GetAllMonthlyTarget;
import com.pullrueepe.model.monthlyTarget.MonthlyTargetRequest;
import com.pullrueepe.model.monthlyTarget.MonthlyTargetResult;
import com.pullrueepe.model.monthlyTarget.ParticularMonthRequest;
import com.pullrueepe.model.monthlyTarget.ParticularMonthTarget;
import com.pullrueepe.model.symbol.GetSymbolResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoints.LOGIN)
    Single<LoginResponse> signIn(@Body LoginRequest loginRequest);

    @POST(ApiEndPoints.GET_SYMBOLS)
    Single<GetSymbolResponse> getSymbol();

    @POST(ApiEndPoints.FUNDS)
        //Call<List<Funds>>getFunds(@Header("clientid") String clientId,)
    Single<Funds> funds(@Header("ClientId") String clientId, @Body FundsRequest fundsRequest);

    @POST(ApiEndPoints.CRETE_FUNDS)
    Single<CreateFund> createFunds(@Header("ClientId") String clientId, @Body CreateFundRequest createFundRequest);

    @POST(ApiEndPoints.GET_MONTHLY_TARGET)
    Single<MonthlyTargetResult> monthlyTarget(@Header("ClientId") String clientId, @Body MonthlyTargetRequest monthlyTargetRequest);

    @POST(ApiEndPoints.CREATE_MONTHLY_TARGET)
    Single<CreateMonthlyTarget> createMonthlyTarget(@Header("ClientId") String clientId, @Body CreateMonthlyRequest createMonthlyRequest);

    @POST(ApiEndPoints.GET_PARTICULAR_MONTH_TARGET)
    Single<ParticularMonthTarget> getParticularMonthTarget(@Header("ClientId") String clientId, @Body ParticularMonthRequest particularMonthRequest);

    @POST(ApiEndPoints.GET_ALL_MONTHLY_TARGETS)
    Single<GetAllMonthlyTarget> getAllMonthTarget(@Header("ClientId") String clientId, @Body GetAllMonthlyRequest particularMonthRequest);

    @POST(ApiEndPoints.GET_HOLDINGS)
    Single<HoldingResult> getHolding(@Header("ClientId") String clientId, @Body HoldingRequest holdingRequest);
}
