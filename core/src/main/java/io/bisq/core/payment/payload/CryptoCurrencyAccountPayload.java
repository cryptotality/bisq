/*
 * This file is part of bisq.
 *
 * bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bisq.core.payment.payload;

import com.google.protobuf.Message;
import io.bisq.generated.protobuffer.PB;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@ToString
@Setter
@Getter
@Slf4j
public final class CryptoCurrencyAccountPayload extends PaymentAccountPayload {
    private String address;

    public CryptoCurrencyAccountPayload(String paymentMethod, String id, long maxTradePeriod) {
        super(paymentMethod, id, maxTradePeriod);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // PROTO BUFFER
    ///////////////////////////////////////////////////////////////////////////////////////////

    private CryptoCurrencyAccountPayload(String paymentMethod,
                                        String id,
                                        long maxTradePeriod,
                                        String address) {
        this(paymentMethod,
                id,
                maxTradePeriod);
        this.address = address;
    }

    @Override
    public Message toProtoMessage() {
        return getPaymentAccountPayloadBuilder()
                .setCryptoCurrencyAccountPayload(PB.CryptoCurrencyAccountPayload.newBuilder()
                        .setAddress(address))
                .build();
    }

    public static CryptoCurrencyAccountPayload fromProto(PB.PaymentAccountPayload proto) {
        return new CryptoCurrencyAccountPayload(proto.getPaymentMethodId(),
                proto.getId(),
                proto.getMaxTradePeriod(),
                proto.getCryptoCurrencyAccountPayload().getAddress());
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String getPaymentDetails() {
        return "Receivers altcoin address: " + address;
    }

    @Override
    public String getPaymentDetailsForTradePopup() {
        return getPaymentDetails();
    }
}
