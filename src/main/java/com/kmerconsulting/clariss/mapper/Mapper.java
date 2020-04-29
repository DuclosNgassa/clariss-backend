package com.kmerconsulting.clariss.mapper;

import com.kmerconsulting.clariss.model.BasisDTO;
import com.kmerconsulting.clariss.model.BasisEntity;

interface Mapper<T extends BasisEntity, S extends BasisDTO> {

    T mapToBasisEntity(S s);

    S mapToBasisDTO(T t);
}
