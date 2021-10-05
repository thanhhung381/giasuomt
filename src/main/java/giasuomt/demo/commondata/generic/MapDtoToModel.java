package giasuomt.demo.commondata.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

//MAP DTO TO MODEL VỚI ĐIỀU KIỆN: TÊN CÁC THUỘC TÍNH CỦA DTO PHẢI GIỐNG VỚI CÁC THUỘC TÍNH ĐÓ Ở MODEL. HOẶC: Nếu dto có thuộc tính nào khác name với model, thì ở model tự viết 1 setter giống với name của thuộc tính đó ở dto.
@Component  //Để khai báo nó thành 1 cái Bean, để cần xài thì mình inject vô để xài
public class MapDtoToModel<E extends Object, T extends Object> {  //map từ thằng E qua thằng T
	public T map(E dto, T model) {
		//Get all getters of dto
		Method[] dtoMethods = dto.getClass().getMethods(); //Lấy ra tất cả các methods (bao gồm các getter,setter) của thằng dto. //import Method của java.lang.reflect.Method;
		List<String> dtoGetterNames = new LinkedList<String>(); //Danh sách tên các getters của dto
		for(Method dtoMethod: dtoMethods) {
			String dtoMethodName = dtoMethod.getName();
			if(!dtoMethodName.equals("getClass") && dtoMethodName.startsWith("get")) //Nếu method bắt đầu là "get" nhưng ko phải là getClass -> thì sẽ là getter
				dtoGetterNames.add(dtoMethodName);
		}
		
		//Map dto property value to the same name of model property
		for(String dtoGetterName: dtoGetterNames) {
			 try { //Try catch là để nếu nó ko map đc (có lỗi) thì bỏ qua làm tiếp chứ ko báo lỗi. (Để phòng TH dto có những property ko cùng name với model thì generic này bỏ qua, để mình tự map manually sau).
				 //get dto property value - lấy giá trị của thuộc tính của dto
				Object dtoPropertyValue = dto.getClass().getMethod(dtoGetterName).invoke(dto); //invoke(dto) là mình truyền vô đối tượng mà mình muốn thực hiện phương thức (là dto)
				//parse dto getter to model setter
				String modelSetterName = dtoGetterName.replaceFirst("get","set"); //Đặt Setter cho model với đk tên các thuộc tính của dto phải giống với thuộc tính đó ở model. HOẶC: Nếu dto có thuộc tính nào khác name với model, thì ở model tự viết 1 setter giống với name của thuộc tính đó ở dto.
				//get properties type
//				Class<?>[] modelSetterPropertyTypeClasses = model.getClass().getMethod(modelSetterName, dtoPropertyValue.getClass()).getParameterTypes();
				//Class<?> modelSetterPropertyType = modelSetterPropertyTypeClasses[0]; //Do mình biết setter nào đó nó sẽ chỉ có 1 tham số.
				Class<?> modelSetterPropertyType = dtoPropertyValue.getClass();
				//map dto property value to the same name of model property
				Set<String> a = new HashSet<>();
				if(modelSetterPropertyType != a.getClass()) {
					model.getClass().getMethod(modelSetterName, modelSetterPropertyType).invoke(model, modelSetterPropertyType.cast(dtoPropertyValue)); //invoke(dto, dtoValue) là mình truyền vô đối tượng mà mình muốn thực hiện phương thức (là dto) và giá trị muốn truyền vào phương thức (là dtoPropertyValue). modelSetterPropertyType.cast là ép về kiểu modelSetterPropertyType
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException 
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return model;
	}
}