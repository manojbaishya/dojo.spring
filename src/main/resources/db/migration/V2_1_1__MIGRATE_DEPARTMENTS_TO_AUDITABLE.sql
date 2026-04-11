UPDATE public.departments
SET public.departments.created_by = 'system'
SET public.departments.last_modified_by = 'system'
SET public.departments.created_date = now()
SET public.departments.last_modified_date = now()
