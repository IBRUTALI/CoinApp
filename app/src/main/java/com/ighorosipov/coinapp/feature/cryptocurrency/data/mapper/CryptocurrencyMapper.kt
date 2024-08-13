package com.ighorosipov.coinapp.feature.cryptocurrency.data.mapper

import com.ighorosipov.coinapp.feature.cryptocurrency.data.dto.model.CryptocurrencyDetailDto
import com.ighorosipov.coinapp.feature.cryptocurrency.data.dto.model.CryptocurrencyDto
import com.ighorosipov.coinapp.feature.cryptocurrency.data.dto.model.detail.DescriptionDto
import com.ighorosipov.coinapp.feature.cryptocurrency.data.dto.model.detail.ImageDto
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.Cryptocurrency
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.CryptocurrencyDetail
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.detail.Description
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.detail.Image
import java.util.Locale

class CryptocurrencyMapper {

    fun cryptocurrencyDtoToDomain(cryptocurrencyDto: CryptocurrencyDto, currencySymbol: String = "$"): Cryptocurrency {
        return Cryptocurrency(
            id = cryptocurrencyDto.id,
            name = cryptocurrencyDto.name,
            image = cryptocurrencyDto.image,
            symbol = cryptocurrencyDto.symbol,
            currencySymbol = currencySymbol,
            currentPrice = cryptocurrencyDto.currentPrice,
            priceChangePercentage24h =  String.format(Locale.ENGLISH, "%.2f", cryptocurrencyDto.priceChangePercentage24h).toDouble()
        )
    }

    fun cryptocurrencyDetailDtoToDomain(cryptocurrencyDetailDto: CryptocurrencyDetailDto): CryptocurrencyDetail {
        return CryptocurrencyDetail(
            id = cryptocurrencyDetailDto.id,
            name = cryptocurrencyDetailDto.name,
            image = imageDtoToDomain(cryptocurrencyDetailDto.image),
            description = descriptionDtoToDomain(cryptocurrencyDetailDto.description),
            categories = cryptocurrencyDetailDto.categories
        )
    }

    private fun imageDtoToDomain(imageDto: ImageDto): Image {
        return Image(
            large = imageDto.large,
            small = imageDto.small,
            thumb = imageDto.thumb
        )
    }

    private fun descriptionDtoToDomain(descriptionDto: DescriptionDto): Description {
        return Description(
            ar = descriptionDto.ar,
            bg = descriptionDto.bg,
            cs = descriptionDto.cs,
            da = descriptionDto.da,
            de = descriptionDto.de,
            el = descriptionDto.el,
            en = descriptionDto.en,
            es = descriptionDto.es,
            fi = descriptionDto.fi,
            fr = descriptionDto.fr,
            he = descriptionDto.he,
            hi = descriptionDto.hi,
            hr = descriptionDto.hr,
            hu = descriptionDto.hu,
            id = descriptionDto.id,
            it = descriptionDto.it,
            ja = descriptionDto.ja,
            ko = descriptionDto.ko,
            lt = descriptionDto.lt,
            nl = descriptionDto.nl,
            no = descriptionDto.no,
            pl = descriptionDto.pl,
            pt = descriptionDto.pt,
            ro = descriptionDto.ro,
            ru = descriptionDto.ru,
            sk = descriptionDto.sk,
            sl = descriptionDto.sl,
            sv = descriptionDto.sv,
            th = descriptionDto.th,
            tr = descriptionDto.tr,
            uk = descriptionDto.uk,
            vi = descriptionDto.vi,
            zh = descriptionDto.zh,
            zhTw = descriptionDto.zhTw
        )
    }

}