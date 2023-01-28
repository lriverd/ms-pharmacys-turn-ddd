package cl.duamit.location.infraestructure.providers.db.location;

import cl.duamit.location.domain.entities.Comuna;
import cl.duamit.location.domain.entities.Provincia;
import cl.duamit.location.domain.entities.Region;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegionMapper {

	@Select("SELECT reg_id, reg_name FROM LOC_REGION")
	@Results(value = {
		@Result(property = "id", column = "reg_id"),
		@Result(property = "name", column = "reg_name")
	})
	List<Region> getRegiones();

	@Select("SELECT pro_id, pro_name FROM LOC_PROVINCIA WHERE reg_id = #{regionId}")
	@Results(value = {
		@Result(property = "id", column = "pro_id"),
		@Result(property = "name", column = "pro_name")
	})
	List<Provincia> getProvincias(@Param("regionId")String regionId);

	@Select("SELECT com_id, com_name FROM LOC_COMUNA WHERE pro_id = #{provinciaId}")
	@Results(value = {
		@Result(property = "id", column = "com_id"),
		@Result(property = "name", column = "com_name")
	})
	List<Comuna> getComunas(@Param("provinciaId")String provinciaId);

}
