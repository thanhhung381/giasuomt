package giasuomt.demo.staff.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.staff.dto.SaveStaffDto;
import giasuomt.demo.staff.dto.UpdateAvatarStaff;
import giasuomt.demo.staff.model.Staff;
import giasuomt.demo.staff.repository.IStaffRepository;
import giasuomt.demo.uploadfile.repository.IAvatarAwsRepository;
import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class StaffService extends GenericService<SaveStaffDto, Staff, Long> implements IStaffService {

	private IStaffRepository iStaffRepository;

	private MapDtoToModel mapDtoToModel;

	private IAvatarAwsRepository iAvatarAwsRepository;

	private AwsClientS3 awsClientS3;

	@Override
	public Staff create(SaveStaffDto dto) {

		Staff staff = new Staff();

		return save(dto, staff);
	}

	@Override
	public Staff update(SaveStaffDto dto) {

		Staff staff = iStaffRepository.getOne(dto.getId());

		String avatarURL = staff.getAvatar();

		awsClientS3.getClient().deleteObject("avatargsomt", avatarURL.substring(avatarURL.lastIndexOf('/') + 1));

		iAvatarAwsRepository.deleteByUrlAvatar(avatarURL);

		return save(dto, staff);
	}

	public Staff save(SaveStaffDto dto, Staff staff) {
		try {

			staff.setAvatar(iAvatarAwsRepository.getById(dto.getIdAvatar()).getUrlAvatar());

			mapDto(dto, staff);

			return iStaffRepository.save(staff);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private void mapDto(SaveStaffDto dto, Staff staff) {
		staff = (Staff) mapDtoToModel.map(dto, staff);

		staff.setFullName(dto.getFullName().toUpperCase());

		staff.setEnglishFullName(StringUltilsForAreaID.removeAccent(dto.getFullName()).toUpperCase());
	}

	@Override
	public Staff updateAvatarStaff(UpdateAvatarStaff dto) {

		try {
			Staff staff = iStaffRepository.getOne(dto.getId());

			String avatarURL = staff.getAvatar();

			awsClientS3.getClient().deleteObject("avatargsomt", avatarURL.substring(avatarURL.lastIndexOf('/') + 1));

			iAvatarAwsRepository.deleteByUrlAvatar(avatarURL);

			staff.setAvatar(iAvatarAwsRepository.getById(dto.getIdAvatar()).getUrlAvatar());

			return iStaffRepository.save(staff);
		} catch (AmazonServiceException e) {

			e.printStackTrace();
		} catch (SdkClientException e) {

			e.printStackTrace();
		}

		return null;
	}

}
