package com.adidas.backend.prioritysaleservice.usecase.mapper;

import com.adidas.backend.prioritysaleservice.commons.client.feign.adiclub.model.AdiClubMemberInfoResponse;
import com.adidas.backend.prioritysaleservice.usecase.model.AdiClubMemberInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdiClubMemberInfoMapper {

  AdiClubMemberInfo map(AdiClubMemberInfoResponse source);

}
