"use strict";
(function () {
    $("#getDealsForm").submit((e) => {
        e.preventDefault();
        let $form = $('#getDealsForm');
        $.ajax({
            url: $form.attr('action'),
            type: 'GET',
            data: $form.serialize(),
            success: (result) => {
                injectCryptoCurrencyDealDetails(result);
            },
            error: (error) => {
                showError(error);
            }
        });
    });

    function injectCryptoCurrencyDealDetails(cryptoCurrencyDeal) {
        $('.cryptoCurrencyName').text(cryptoCurrencyDeal.cryptoCurrencyName);

        $('.cryptoCurrencyBuyPrice').text(cryptoCurrencyDeal.buyPrice.price);
        $('.cryptoCurrencySellPrice').text(cryptoCurrencyDeal.sellPrice.price);
        $('.cryptoCurrencyBuyTime').text(cryptoCurrencyDeal.buyPrice.time);
        $('.cryptoCurrencySellTime').text(cryptoCurrencyDeal.sellPrice.time);
        $('.cryptoCurrencyProfit').text(cryptoCurrencyDeal.profit);

        $('#cryptoCurrencyInfo').removeClass('hidden');
        $('.error-text').addClass('hidden');
    }

    function showError(error) {
        let $errorText = $('.error-text');
        $errorText.text('Unfortunately couldn\'t fetch cryptocurrency deal details, error: ' + error.responseJSON.message);
        $('#cryptoCurrencyInfo').addClass('hidden');
        $errorText.removeClass('hidden');
    }
})();
