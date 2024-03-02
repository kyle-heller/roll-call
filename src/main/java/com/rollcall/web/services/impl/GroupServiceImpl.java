package com.rollcall.web.services.impl;

import com.rollcall.web.models.Group;
import com.rollcall.web.dto.GroupDto;
import com.rollcall.web.repository.GroupRepository;
import com.rollcall.web.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;


    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<GroupDto> findAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream().map(this::mapToGroupDto).collect(Collectors.toList());
    }

    private GroupDto mapToGroupDto(Group group) {
        GroupDto groupDto = GroupDto.builder()
                .id(group.getId())
                .title(group.getTitle())
                .content(group.getContent())
                .photoURL(group.getPhotoURL())
                .createdOn(group.getCreatedOn())
                .updatedOn(group.getUpdatedOn())
                .build();
        return groupDto;
    }
}