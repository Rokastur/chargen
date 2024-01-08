import React, { useEffect, useState } from "react";
import CharacterService from "../services/character.service";
import AlignmentService from "../services/alignment.service";
import RaceService from "../services/race.serivce";
import { Link } from "react-router-dom";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";

const Character = () => {
  const [characters, setCharacters] = useState([]);
  const [characterName, setCharacterName] = useState("");
  const [characterAlignment, setCharacterAlignment] = useState("");
  const [characterRace, setCharacterRace] = useState("");
  const [availableAlignments, setAvailableAlignments] = useState([]);
  const [availableRaces, setAvailableRaces] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const characterData = {
        name: characterName,
        alignment: characterAlignment,
        race: characterRace,
      };
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
    } catch (error) {
      console.error("Error creating character: ", error);
    }
  };

  const onCharacterNameChange = (e) => setCharacterName(e.target.value);
  const onCharacterAlignmentChange = (e) =>
    setCharacterAlignment(e.target.value);
  const onCharacterRaceChange = (e) => setCharacterRace(e.target.value);

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

  return (
    <div>
      {characters.map((character) => (
        <div key={character.id}>
          <Link to={`/character/${character.id}`}>{character.name}</Link>
        </div>
      ))}

      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="characterName">Character Name</label>
          <input
            value={characterName}
            onChange={onCharacterNameChange}
            required
            type="text"
            name="characterName"
            id="characterName"
          />
        </div>

        <FormControl>
          <FormLabel id="alignment-selection-group-label">Alignment</FormLabel>
          <RadioGroup
            aria-labelledby="alignment-selection-group-label"
            defaultValue="NEUTRAL"
            name="radio-buttons-group"
            value={characterAlignment}
            onChange={(e) => setCharacterAlignment(e.target.value)}
          >
            {availableAlignments.map((alignment) => (
              <FormControlLabel
                key={alignment.name} // Assuming 'name' is unique for each alignment
                value={alignment.name}
                control={<Radio />}
                label={alignment.name.replace(/_/g, " ").toLowerCase()}
              />
            ))}
          </RadioGroup>
        </FormControl>

        <FormControl>
          <FormLabel id="race-selection-group-label">Race</FormLabel>
          <RadioGroup
            aria-labelledby="race-selection-group-label"
            defaultValue="HUMAN"
            name="radio-buttons-group"
            value={characterRace}
            onChange={(e) => setCharacterRace(e.target.value)}
          >
            {availableRaces.map((race) => (
              <FormControlLabel
                key={race.name} // Assuming 'name' is unique for each alignment
                value={race.name}
                control={<Radio />}
                label={race.name.replace(/_/g, " ").toLowerCase()}
              />
            ))}
          </RadioGroup>
        </FormControl>

        <button type="submit">Create New Character</button>
      </form>
    </div>
  );
};

export default Character;
