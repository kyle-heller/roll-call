package com.rollcall.web.services;

import com.rollcall.web.dto.GroupDto;
import com.rollcall.web.models.Group;
import org.springframework.stereotype.Service;

import java.util.List;
public interface GroupService {
    List<GroupDto> findAllGroups();

    Group saveGroup(GroupDto groupDto);

    GroupDto findClubById(long groupId);

    void updateGroup(GroupDto group);
}
