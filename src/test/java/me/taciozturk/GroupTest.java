package me.taciozturk;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {
    @Test
    public void testAddMember() {
        User user = new User();
        Group group = new Group("Test Group");
        group.addMember(user);
        assertTrue(group.getMembers().contains(user));
    }

    @Test
    public void testRemoveMember() {
        User user = new User();
        Group group = new Group("Test Group");
        group.addMember(user);
        group.removeMember(user);
        assertFalse(group.getMembers().contains(user));
    }

    @Test
    public void testGetGroups() {
        Group group1 = new Group("Group 1");
        Group group2 = new Group("Group 2");
        ArrayList<Group> groups = Group.getGroups();
        assertTrue(groups.contains(group1));
        assertTrue(groups.contains(group2));
    }

    @Test
    public void testGetGroupByID() {
        Group group1 = new Group("Group 1");
        Group group2 = new Group("Group 2");
        assertEquals(group1, Group.getGroup(group1.getGroupID()));
        assertEquals(group2, Group.getGroup(group2.getGroupID()));
    }
}