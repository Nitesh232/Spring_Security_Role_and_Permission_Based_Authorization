package comniteshspringbootentity;




import javautilCollections;
import javautilList;
import javautilSet;
import javautilstreamCollectors;

import orgspringframeworksecuritycoreauthoritySimpleGrantedAuthority;

import lombokGetter;
import lombokRequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
	
	
	USER(CollectionsemptySet()),
	
	ADMIN(
	
			Setof(
			
					 ADMIN_READ,
					 ADMIN_CREATE,
					 ADMIN_UPDATE,
					 ADMIN_DELETE,
					 MANAGER_READ,
					 MANAGER_CREATE,
					 MANAGER_UPDATE,
					 MANAGER_DELETE
					
			)
			
	),
	
	MANAGER(
			
			Setof(
			
					 MANAGER_READ,
					 MANAGER_CREATE,
					 MANAGER_UPDATE,
					 MANAGER_DELETE
					
			)
			
		)
	;
	
	
	@Getter
	private final Set<Role>  roles;
	
	
	public List<SimpleGrantedAuthority> getAuthorities(){
		
		List<SimpleGrantedAuthority> authorities = getroles()
		stream()
		map( s -> new SimpleGrantedAuthority( sgetroles()))
		collect(CollectorstoList());
		
		authoritiesadd(new SimpleGrantedAuthority("ROLE_"+this.name()));
		
		return authorities;
		
	}

}
