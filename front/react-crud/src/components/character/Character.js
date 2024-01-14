import React, { useEffect, useState } from "react";
import CharacterService from "../../services/character.service";
import AlignmentService from "../../services/alignment.service";
import RaceService from "../../services/race.serivce";
import AbilityService from "../../services/ability.service";
import DiceRollDisplay from "./DiceRollDisplay";
import CharacterForm from "./CharacterForm";
import { Link } from "react-router-dom";
import Chip from "@mui/material/Chip";
import CasinoOutlinedIcon from "@mui/icons-material/CasinoOutlined";

const Character = () => {
  const [characters, setCharacters] = useState([]);
  const [characterName, setCharacterName] = useState("");
  const [characterAlignment, setCharacterAlignment] = useState("");
  const [characterRace, setCharacterRace] = useState("");
  const [availableAlignments, setAvailableAlignments] = useState([]);
  const [availableRaces, setAvailableRaces] = useState([]);
  const [availableAbilities, setAvailableAbilities] = useState([]);
  const [abilityValues, setAbilityValues] = useState({});
  const [diceRolls, setDiceRolls] = useState([]);
  const [abilityErrors, setAbilityErrors] = useState({});

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateAssignments() || Object.keys(abilityErrors).length > 0) {
      console.error("Cannot submit, errors in ability assignments");
      return;
    }

    try {
      const characterData = {
        name: characterName,
        alignment: characterAlignment,
        race: characterRace,
        abilities: abilityValues,
      };
      console.log("Data to send: ", characterData);
      const response = await CharacterService.createNewCharacter(characterData);
      console.log("Character created: ", response.data);
      // Add the new campaign to the campaigns state
      setCharacters((existingCharacters) => [
        ...existingCharacters,
        response.data,
      ]);
      // Optionally reset form fields
      setCharacterName("");
      setCharacterAlignment("");
      setCharacterRace("");
      setAbilityValues({});
    } catch (error) {
      console.error("Error creating character: ", error);
    }
  };

  const onCharacterNameChange = (e) => setCharacterName(e.target.value);

  const handleDiceRoll = () => {
    // Determine the number of abilities
    const numberOfRolls = availableAbilities.length;

    // Roll the dice for each ability
    const rolls = Array.from({ length: numberOfRolls }, () => {
      // Assuming a six-sided dice
      return Math.floor(Math.random() * 6) + 1;
    });

    setDiceRolls(rolls);

    // Reset ability values and errors when new roll happens
    setAbilityValues({});
    setAbilityErrors({});
  };

  useEffect(() => {
    const numberOfRolls = availableAbilities.length;
    const rolls = Array.from(
      { length: numberOfRolls },
      () => Math.floor(Math.random() * 6) + 1
    );
    setDiceRolls(rolls);
  }, [availableAbilities]);

  const calculateRollUsages = () => {
    // Count the total occurrences of each roll
    const totalUsages = diceRolls.reduce((acc, roll) => {
      acc[roll] = (acc[roll] || 0) + 1;
      return acc;
    }, {});

    // Subtract the number of times each roll has been used
    Object.values(abilityValues).forEach((value) => {
      if (value) {
        totalUsages[value]--;
      }
    });

    return totalUsages;
  };

  useEffect(() => {
    CharacterService.listOwnedCharacters()
      .then((response) => {
        setCharacters(response.data);
      })
      .catch((error) => {
        console.error("Error fetching characters: ", error.response || error);
      });
  }, []);

  useEffect(() => {
    AlignmentService.listAlignmentsByRuleset("DND5e")
      .then((response) => {
        setAvailableAlignments(response.data);
      })
      .catch((error) => {
        console.error("Error fetching alignments: ", error.response || error);
      });
  }, []);

  useEffect(() => {
    RaceService.listRacesByRuleset("DND5e")
      .then((response) => {
        setAvailableRaces(response.data);
      })
      .catch((error) => {
        console.error("Error fetching races: ", error.response || error);
      });
  }, []);

  useEffect(() => {
    AbilityService.listAbilityByRuleset("DND5e")
      .then((response) => {
        setAvailableAbilities(response.data);
        // Initialize ability values
        const initialAbilityValues = response.data.reduce((acc, ability) => {
          acc[ability.name] = ""; // Initialize each ability value as an empty string
          return acc;
        }, {});
        setAbilityValues(initialAbilityValues);
      })
      .catch((error) => {
        console.error("Error fetching abilities: ", error.response || error);
      });
  }, []);

  const validateAssignments = () => {
    const usageCount = diceRolls.reduce((acc, number) => {
      acc[number] = (acc[number] || 0) + 1;
      return acc;
    }, {});

    const newAbilityErrors = {};
    Object.entries(abilityValues).forEach(([ability, value]) => {
      if (value && (usageCount[value] || 0) <= 0) {
        newAbilityErrors[ability] = "Roll already assigned";
      } else if (value) {
        usageCount[value]--;
      }
    });

    setAbilityErrors(newAbilityErrors);
    return Object.keys(newAbilityErrors).length === 0;
  };

  // Handle ability value change
  const handleAbilityChange = (abilityName, value) => {
    setAbilityValues((prevValues) => ({
      ...prevValues,
      [abilityName]: value,
    }));
  };

  return (
    <div>
      {characters.map((character) => (
        <div key={character.id}>
          <Link to={`/character/${character.id}`}>{character.name}</Link>
        </div>
      ))}

      <Chip
        label="roll the dice"
        variant="outlined"
        icon={<CasinoOutlinedIcon />}
        onClick={handleDiceRoll}
      />

      <DiceRollDisplay diceRolls={diceRolls} />

      <form onSubmit={handleSubmit}>
        <CharacterForm
          characterName={characterName}
          onCharacterNameChange={onCharacterNameChange}
          characterAlignment={characterAlignment}
          setCharacterAlignment={setCharacterAlignment}
          characterRace={characterRace}
          setCharacterRace={setCharacterRace}
          availableAlignments={availableAlignments}
          availableRaces={availableRaces}
          availableAbilities={availableAbilities}
          abilityValues={abilityValues}
          handleAbilityChange={handleAbilityChange}
          abilityErrors={abilityErrors}
          diceRolls={diceRolls}
          calculateRollUsages={calculateRollUsages}
        />

        <button type="submit">Create New Character</button>
      </form>
    </div>
  );
};
export default Character;
