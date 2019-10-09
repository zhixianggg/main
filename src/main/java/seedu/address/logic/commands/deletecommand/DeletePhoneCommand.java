package seedu.address.logic.commands.deletecommand;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PanelType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.phone.Phone;

/**
 * Deletes a phone identified using it's displayed index from the seller manager.
 */
public class DeletePhoneCommand extends Command {

    public static final String COMMAND_WORD = "delete-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the phone identified by the index number used in the displayed phone list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PHONE_SUCCESS = "Deleted Phone: %1$s";

    private final Index targetIndex;

    public DeletePhoneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Phone> lastShownList = model.getFilteredPhoneList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PHONE_DISPLAYED_INDEX);
        }

        Phone phoneToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePhone(phoneToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PHONE_SUCCESS, phoneToDelete), PanelType.PHONE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePhoneCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePhoneCommand) other).targetIndex)); // state check
    }
}
