package com.api.Services;

import com.api.ModelVO.AreaVO;
import java.util.List;

public interface IAreaService {

    AreaVO searchArea(Long area);

    List<AreaVO> listAreas();

    List<AreaVO> filerAreas(String area);


}
