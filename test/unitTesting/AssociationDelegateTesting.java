package unitTesting;

import Domain.Game.Team;
import Domain.League.SchedulingPolicyAllTeamsPlayTwice;
import Domain.League.ScorePolicy;
import Domain.League.Season;
import Exception.*;
import Service.SystemController;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AssociationDelegateTesting {

    SystemController controller = new SystemController("controller Test");
    Date birthDate = new Date(1995, 3, 6);

    @Before
    public void init() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException {
        controller.signIn("AssociationDelegate", "Daniel@gmail.com", "abc", birthDate);
        controller.addAssociationDelegate("Daniel@gmail.com");
        controller.logIn("associationDelegate@gmail.com", "abc");
    }


    @Test
    public void insertSchedulingPolicyTest() throws PasswordDontMatchException, MemberNotExist, DontHavePermissionException {
        controller.logIn("associationDelegate@gmail.com", "abc");
        String league = "women league";
        String season = "2020";
        String sPolicy = "All teams play each other once";
        //check if it is used
    }

    @Test
    public void changeScorePolicyTest() throws ObjectNotExist, IncorrectInputException, DontHavePermissionException, AlreadyExistException, MemberNotExist, PasswordDontMatchException {
        //controller.logIn("associationDelegate@gmail.com", "abc");
        String league = "women league";
        controller.setLeague(league);
        String season = "2020";
        controller.setLeagueByYear(league, season);
        String sWinning = "10";
        String sDraw = "5";
        String sLosing = "2";
        controller.changeScorePolicy(league, season, sWinning, sDraw, sLosing);
        ScorePolicy scorePolicy = (ScorePolicy) controller.getScorePolicy(league, season);
        assertEquals(scorePolicy.getScoreToWinningTeam(),sWinning);
        assertEquals(scorePolicy.getScoreToDrawGame(),sDraw);
        assertEquals(scorePolicy.getScoreToLosingTeam(),sLosing);
    }


    @Test
    public void addRefereeToLeagueInSeason() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, ObjectNotExist {
        String league = "women league";
        controller.setLeague(league);
        String season = "2020";
        controller.setLeagueByYear(league, season);
        String refereeName = "Noa";
        controller.addRefereeToLeagueInSeason(league, season, refereeName);
        Season seasonObj = new Season(season);
        assertTrue(controller.getLeague(league).getLeagueInSeason(seasonObj).getReferees().containsKey(refereeName));

    }

    @Test(expected = ObjectNotExist.class)
    public void addRefereeToLeagueInSeasonTrowException() throws IncorrectInputException, DontHavePermissionException, AlreadyExistException, ObjectNotExist {
        String league = "women league";
        controller.setLeague(league);
        String season = "2020";
        controller.setLeagueByYear(league, season);
        String refereeName = "Noa";
        controller.addRefereeToLeagueInSeason("men league", season, refereeName);
    }

    @Test
    public void setSchedulingPolicyToLeagueInSeasonTest() throws ObjectNotExist, IncorrectInputException, DontHavePermissionException {
        String league = "women league";
        String season = "2020";
        String policyName = "All teams play each other twice";
        controller.setSchedulingPolicyToLeagueInSeason(league, season, policyName);
        assertThat(controller.getSchedulingPolicyInLeagueInSeason(league,season),instanceOf(SchedulingPolicyAllTeamsPlayTwice.class));
    }

    @Test(expected = IncorrectInputException.class)
    public void setSchedulingPolicyToLeagueInSeasonTestThrownException() throws ObjectNotExist, IncorrectInputException, DontHavePermissionException {
        String league = "women league";
        String season = "2020";
        String policyName = "All teams play each other every day";
        controller.setSchedulingPolicyToLeagueInSeason(league, season, policyName);
    }

    @Test
    public void addTeamToLeagueInSeasonTest() throws DontHavePermissionException, IncorrectInputException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, MemberNotExist {
        String league = "women league";
        controller.setLeague(league);
        String season = "2020";
        controller.setLeagueByYear(league, season);
        String teamName = "QueenB";
        controller.addTeam(teamName, "Noa");
        Team team = controller.getTeams().get(teamName);
        controller.addTeamToLeagueInSeason(league, season, teamName);
        Season seasonObj = new Season(season);
        assertTrue(controller.getLeague(league).getLeagueInSeason(seasonObj).getTeams().contains(team));

    }

    @Test (expected = AlreadyExistException.class)
    public void addTeamToLeagueInSeasonTestThrownException() throws DontHavePermissionException, IncorrectInputException, ObjectNotExist, ObjectAlreadyExist, AlreadyExistException, MemberNotExist {
        String league = "women league";
        controller.setLeague(league);
        String season = "2020";
        controller.setLeagueByYear(league, season);
        String teamName = "QueenB";
        controller.addTeamToLeagueInSeason(league, season, teamName);
        controller.addTeamToLeagueInSeason(league, season, teamName);
    }
}

