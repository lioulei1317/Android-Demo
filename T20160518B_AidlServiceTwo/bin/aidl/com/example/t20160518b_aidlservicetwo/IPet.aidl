package	com.example.t20160518b_aidlservicetwo;
import com.example.t20160518b_aidlservicetwo.Pet;
import com.example.t20160518b_aidlservicetwo.Person;
interface IPet
{
	List<Pet> getPets(in Person owner);
}