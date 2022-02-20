package com.developermind.phonebook.annotations.sequence;

import javax.validation.GroupSequence;

import com.developermind.phonebook.annotations.group.NotBlankGroup;
import com.developermind.phonebook.annotations.group.NotEmptyGroup;
import com.developermind.phonebook.annotations.group.NotNullGroup;
import com.developermind.phonebook.annotations.group.SizeGroup;

@GroupSequence(value = { NotNullGroup.class, NotEmptyGroup.class, NotBlankGroup.class, SizeGroup.class })
public interface ValidationSequence {

}
